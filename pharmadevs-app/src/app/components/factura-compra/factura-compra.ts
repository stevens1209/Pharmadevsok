import { Component, ElementRef, TemplateRef, ViewChild } from '@angular/core';
import Swal from 'sweetalert2';
import { FacturaCompra } from '../../model/factura-compra.model';
import { Proveedor } from '../../model/proveedor.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { FacturaCompraService } from '../../services/factura-compra';
import { ProveedorService } from '../../services/proveedor';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-factura-compra',
  standalone: false,
  templateUrl: './factura-compra.html',
  styleUrl: './factura-compra.css'
})
export class FacturaCompraComponent {
  facturaCompras: FacturaCompra[]=[]
  proveedores: Proveedor[] = [];
  facturaCompra: FacturaCompra = {} as FacturaCompra;
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<FacturaCompra>;
  selectedFile!: File;
  imagenPreview: string = "";
  facturaSeleccionada: FacturaCompra | null = null;

  mostrarColumnas: String[] = ['detalles','idFacturacompra','fecha','montototal','faccompra','proveedor','acciones']
//venta
  @ViewChild('formularioFacturaCompra') formularioFacturaCompra!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalFacturaCompra') modalFacturaCompra!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;


  constructor(
    private facturaCompraService: FacturaCompraService,
    private proveedorService: ProveedorService,
    private dialog: MatDialog,
    private http: HttpClient
  ){}

  ngOnInit(): void {
    this.findAll();
    this.cargarProveedores();
  }

  findAll(): void {
    this.facturaCompraService.findAll().subscribe(data=>{
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  cargarProveedores(): void{
    this.proveedorService.findAll().subscribe(data=>{
      this.proveedores = data;
    });
  }


  save(): void{
    this.facturaCompraService.save(this.facturaCompra).subscribe(data=>{
      this.facturaCompra = {} as FacturaCompra;
      this.findAll();
    });
  }

  update(): void{
    if(this.idEditar !== null){
      this.facturaCompraService.update(this.idEditar, this.facturaCompra).subscribe(()=>{
        this.facturaCompra = {} as FacturaCompra;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void{
    Swal.fire({
      title: '¿Desea eliminar la factura?',
      text: 'Esta accion no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.facturaCompraService.delete(this.facturaCompra.idFacturacompra).subscribe(() => {
          this.findAll();
          this.facturaCompra = {} as FacturaCompra;
          Swal.fire('Eliminado', 'El libro ha sido eliminado', 'success')
        });
      }else{
        this.facturaCompra = {} as FacturaCompra;
      }
    });
  }


  editarFacturaCompra(facturaCompra : FacturaCompra): void{
    this.facturaCompra = {...facturaCompra}
    this.idEditar = facturaCompra.idFacturacompra;
    this.editar = true;
    setTimeout(() =>{
      this.formularioFacturaCompra.nativeElement.scrollIntoView({behavior: 'smooth', block: 'start'})
    }),100;
  }

  editarFacCompraCancelar(form: NgForm): void{
    this.facturaCompra = {} as FacturaCompra;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarFacCompra(): void{
    if(this.editar && this.idEditar!== null){
      this.update();
    }else{
      this.save();
    }
    this.dialog.closeAll();
  }

  filtroFacCompra(event: Event): void{
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  nombreCompletoProveedor(proveedor: Proveedor): string{
    return `${proveedor.nombre}`;
  }

  abrirModal(facturaCompra?: FacturaCompra): void{
    if(facturaCompra){
      this.facturaCompra = {...facturaCompra};
      this.editar = true;
      this.idEditar = facturaCompra.idFacturacompra;
  }else{
    this.facturaCompra = {} as FacturaCompra;
    this.editar = false;
    this.idEditar= null;
  }
  this.dialog.open(this.modalFacturaCompra, {
    width: '800px',
    disableClose: true
  });
}

compararProveedores(p1: Proveedor, p2: Proveedor): boolean{
  return p1 && p2 ? p1.idProveedor === p2.idProveedor : p1 === p2;
}

onFileSelected(event: any){
  this.selectedFile = event.target.files[0];
}

subirfactura(): void{
  if(!this.selectedFile){return;}
  const formData = new FormData
  formData.append("file", this.selectedFile);

  if(this.facturaCompra.faccompra){
    formData.append("oldFac", this.facturaCompra.faccompra);
  }

  this.http.post<{ruta: string}>('http://localhost:8080/api/upload-factura', formData)
    .subscribe(res => {
      this.facturaCompra.faccompra = res.ruta;
      this.imagenPreview = res.ruta;
    });
}

abrirModalDetalles(facturaCompra: FacturaCompra): void {
  this.facturaSeleccionada = facturaCompra;
  this.dialog.open(this.modalDetalles, {
    width: '500px'
  });
}

cerrarModal(): void{
  this.dialog.closeAll();
  this.facturaSeleccionada = null;
}
}