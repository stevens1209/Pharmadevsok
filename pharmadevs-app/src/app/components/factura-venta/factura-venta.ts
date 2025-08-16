import { Component, ElementRef, TemplateRef, ViewChild } from '@angular/core';
import { FacturaVenta } from '../../model/factura-venta.model';
import { Cliente } from '../../model/cliente.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { FacturaVentaService } from '../../services/factura-venta';
import { ClienteService } from '../../services/cliente';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-factura-venta',
  standalone: false,
  templateUrl: './factura-venta.html',
  styleUrl: './factura-venta.css'
})
export class FacturaVentaComponent {
  facturaventas: FacturaVenta[]=[]
  clientes: Cliente[] = [];
  facturaventa: FacturaVenta = {} as FacturaVenta;
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<FacturaVenta>;
  selectedFile!: File;
  imagenPreview: string = "";
  facturaSeleccionada: FacturaVenta | null = null;

  mostrarColumnas: String[] = ['detalles','idFacturaventa','fecha','montototal','facventa','cliente','acciones']

  @ViewChild('formularioFacturaVenta') formularioFacturaVenta!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalFacturaVenta') modalFacturaVenta!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;


  constructor(
    private facturaVentaService: FacturaVentaService,
    private clienteService: ClienteService,
    private dialog: MatDialog,
    private http: HttpClient
  ){}

  ngOnInit(): void {
    this.findAll();
    this.cargarClientes();
  }

  findAll(): void {
    this.facturaVentaService.findAll().subscribe(data=>{
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  cargarClientes(): void{
    this.clienteService.findAll().subscribe(data=>{
      this.clientes = data;
    });
  }


  save(): void{
    this.facturaVentaService.save(this.facturaventa).subscribe(data=>{
      this.facturaventa = {} as FacturaVenta;
      this.findAll();
    });
  }

  update(): void{
    if(this.idEditar !== null){
      this.facturaVentaService.update(this.idEditar, this.facturaventa).subscribe(()=>{
        this.facturaventa = {} as FacturaVenta;
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
        this.facturaVentaService.delete(this.facturaventa.idFacturaventa).subscribe(() => {
          this.findAll();
          this.facturaventa = {} as FacturaVenta;
          Swal.fire('Eliminado', 'El libro ha sido eliminado', 'success')
        });
      }else{
        this.facturaventa = {} as FacturaVenta;
      }
    });
  }


  editarFacturaVenta(facturaventa : FacturaVenta): void{
    this.facturaventa = {...facturaventa}
    this.idEditar = facturaventa.idFacturaventa;
    this.editar = true;
    setTimeout(() =>{
      this.formularioFacturaVenta.nativeElement.scrollIntoView({behavior: 'smooth', block: 'start'})
    }),100;
  }

  editarFacVentaCancelar(form: NgForm): void{
    this.facturaventa = {} as FacturaVenta;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarFacventa(): void{
    if(this.editar && this.idEditar!== null){
      this.update();
    }else{
      this.save();
    }
    this.dialog.closeAll();
  }

  filtroFacventa(event: Event): void{
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  nombreCompletoCliente(cliente: Cliente): string{
    return `${cliente.nombre}`;
  }

  abrirModal(facturaventa?: FacturaVenta): void{
    if(facturaventa){
      this.facturaventa = {...facturaventa};
      this.editar = true;
      this.idEditar = facturaventa.idFacturaventa;
  }else{
    this.facturaventa = {} as FacturaVenta;
    this.editar = false;
    this.idEditar= null;
  }
  this.dialog.open(this.modalFacturaVenta, {
    width: '800px',
    disableClose: true
  });
}

compararClientes(c1: Cliente, c2: Cliente): boolean{
  return c1 && c2 ? c1.idCliente === c2.idCliente : c1 === c2;
}

onFileSelected(event: any){
  this.selectedFile = event.target.files[0];
}

subirfactura(): void{
  if(!this.selectedFile){return;}
  const formData = new FormData
  formData.append("file", this.selectedFile);

  if(this.facturaventa.facventa){
    formData.append("oldFac", this.facturaventa.facventa);
  }

  this.http.post<{ruta: string}>('http://localhost:8080/api/upload-factura', formData)
    .subscribe(res => {
      this.facturaventa.facventa = res.ruta;
      this.imagenPreview = res.ruta;
    });
}

abrirModalDetalles(facturaventa: FacturaVenta): void {
  this.facturaSeleccionada = facturaventa;
  this.dialog.open(this.modalDetalles, {
    width: '500px'
  });
}

cerrarModal(): void{
  this.dialog.closeAll();
  this.facturaSeleccionada = null;
}
}