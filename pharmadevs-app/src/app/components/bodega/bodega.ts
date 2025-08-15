import { Component, ElementRef, OnInit,TemplateRef, ViewChild } from '@angular/core';
import { Bodega } from '../../model/bodega.model';
import { MatTableDataSource } from '@angular/material/table';
import { BodegaService } from '../../services/bodega';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ViewportRuler } from '@angular/cdk/scrolling';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { Proveedor } from '../../model/proveedor.model';
import { ProveedorService } from '../../services/proveedor';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-bodega',
  standalone: false,
  templateUrl: './bodega.html',
  styleUrl: './bodega.css'
})
export class BodegaComponent implements OnInit {
  
  bodegas: Bodega[]=[];
  proveedores: Proveedor []=[];
  bodega: Bodega = {} as Bodega; 
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<Bodega>;
  selectFile: any;
  bodegaSeleccionado: Bodega | null = null;


  
  mostrarColumnas: string[] = ['detalles','idBodega', 'nombrematerial', 'cantidad', 'fecharecepcion', 'proveedor', 'acciones'];

  @ViewChild('formularioBodega') formularioBodega!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalBodega') modalBodega!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;
  
  constructor(
    private bodegaService: BodegaService,
    private proveedorService: ProveedorService,
    private dialog: MatDialog, 
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.findAll();
    this.cargarProveedor();
  }

  findAll(): void {
    this.bodegaService.findAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  cargarProveedor(): void {
    this.proveedorService.findAll().subscribe(data => {
      this.proveedores = data ;
    });
  }

  save(): void {
    this.bodegaService.save(this.bodega).subscribe(() => {
      this.bodega = {} as Bodega;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.bodegaService.update(this.idEditar, this.bodega).subscribe(() => {
        this.bodega = {} as Bodega;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void {
    Swal.fire({
    title: 'Desea eliminar el dato?',
    text: 'esta opcion no se puede deshacer',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText:'Si, eliminar',
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6'
  }).then((result) => {
    if (result.isConfirmed) {
      this.bodegaService.delete(this.bodega.idBodega).subscribe(() => {
        this.findAll();
        Swal.fire('Eliminado!', 'La bodega ha sido eliminada.', 'success');
      });
    }else {
      this.bodega = {} as Bodega;
    }
  });
}

editarBodega(bodega: Bodega): void {
    this.bodega = { ...bodega };
    this.editar = true;
    this.idEditar = bodega.idBodega;
    this.formularioBodega.nativeElement.scrollIntoView({ behavior: 'smooth' });

    setTimeout(() => {
      if(this.formularioBodega?.nativeElement){
      this.formularioBodega.nativeElement.scrollIntoView({ behavior: 'smooth' });
      }
  }, 100);
  }

  editarBodegaCancelar(form: NgForm): void {
    this.bodega = {} as Bodega;
    this.editar = false;
    this.idEditar = null;
    form.resetForm();
  }

  guardarBodega(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    }else{
      this.save();
      form.resetForm();
    }
    this.dialog.closeAll();
  }

  buscarBodega(event: Event) {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  filtroBodega(event: Event):void{
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  nombreProveedor(proveedor:Proveedor): string{
    return `${proveedor?.nombre ?? ''}`;
  }

  abrirModal(bodega?: Bodega): void {
    if (bodega) {
      this.bodega = { ...bodega }; // <-- copiar el parámetro correcto
      this.editar = true;
      this.idEditar = bodega.idBodega;
    } else {
      this.bodega = {} as Bodega;
      this.editar = false;
      this.idEditar = null;
    }

    this.dialog.open(this.modalBodega, {
      width: '800px',
      disableClose: true
    });
  }

  compararProveedor(p1: Proveedor, p2: Proveedor): boolean {
    return p1 && p2 ? p1.idProveedor === p2.idProveedor : p1 === p2;
  }

  onFileSelected(event: any): void {
    this.selectFile = event.target.files[0];
  }

  

  abrirModalDetalles(bodega: Bodega): void {
    this.bodegaSeleccionado =bodega;
    this.dialog.open(this.modalDetalles, { // <-- abrir el modal de detalles correcto
      width: '500px'
    });
  }

  cerrarModal(): void {
    this.dialog.closeAll();
    this.bodegaSeleccionado = null;
  }

 
}