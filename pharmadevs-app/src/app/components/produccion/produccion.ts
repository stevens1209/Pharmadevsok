import { Component, OnInit, AfterViewInit, TemplateRef, ViewChild, ElementRef } from '@angular/core';
import { Produccion } from '../../model/produccion.model';
import { Producto } from '../../model/producto.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ProduccionService } from '../../services/produccion';
import { ProductoService } from '../../services/producto';
import { MatDialog } from '@angular/material/dialog';
import { DatePipe } from '@angular/common';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-produccion',
  standalone: false,
  templateUrl: './produccion.html',
  styleUrl: './produccion.css'
})
export class ProduccionComponent implements OnInit{

  producciones: Produccion[] = [];
  productos: Producto[] = [];
  produccion: Produccion = {} as Produccion;
  editar = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<Produccion>;
  selectedFile!: File;
  imagenPreview: string = "";
  produccionSeleccionado: Produccion | null = null;
  mostrarColumnas: string[] = ['detalles', 'idProduccion', 'fechaproduccion', 'fechavencimiento', 'lote', 'cantidadproducida', 'producto', 'acciones'];


  @ViewChild('formularioProduccion') formularioProduccion!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalProduccion') modalProducccion!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private produccionService: ProduccionService,
    private productoService: ProductoService,
    public dialog: MatDialog,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.findAll();
    this.cargarProductos();
  }

  findAll(): void {
    this.produccionService.findAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;    
        this.dataSource.sort = this.sort;
    
    });
  }

  cargarProductos(): void {
    this.productoService.findAll().subscribe(data => {
      this.productos = data;
    });
  }

  save(): void {
    this.produccionService.save(this.produccion).subscribe(() => {
      this.produccion = {} as Produccion;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.produccionService.update(this.idEditar, this.produccion).subscribe(() => {
        this.produccion = {} as Produccion;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void {
    Swal.fire({
      title: '¿Desea eliminar la producción?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then(result => {
      if (result.isConfirmed) {
        this.produccionService.delete(this.produccion.idProduccion).subscribe(() => {
          this.findAll();
          Swal.fire('Eliminado', 'La producción ha sido eliminada', 'success');
        });
      }else{
        this.produccion = {} as Produccion;
      }
    });
  }

  editarProduccion(produc: Produccion): void {
    this.produccion = { ...produc };
    this.idEditar = produc.idProduccion;
    this.editar = true;

    // Scroll suave al formulario (usa el wrapper #formularioAlmacen)
    setTimeout(() => {
      if (this.formularioProduccion?.nativeElement) {
        this.formularioProduccion.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    }, 100);
  }

  editarProduccionCancelar(form: NgForm): void {
    this.produccion = {} as Produccion;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

   guardarProduccion(): void {
    if (this.editar && this.idEditar ! == null) {
      this.update();
      }else{
        this.save;
      }
      this.dialog.closeAll();
    }

    filtroProduccion(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
     
  }

  nombreProducto(producto: Producto): string {
    return `${producto?.nombre ?? ''}`;
  }

  abrirModalProduccion(produccion?: Produccion): void {
    if (produccion) {
      this.produccion = { ...produccion };
      this.editar = true;
      this.idEditar = produccion.idProduccion;
    } else {
      this.produccion = {} as Produccion;
      this.editar = false;
      this.idEditar = null;
    }
    this.dialog.open(this.modalProducccion,{
      width: '800px',
      disableClose: true
    });
  }

  compararProducto(p1:Producto, p2:Producto): boolean{
    return p1 && p2 ? p1.idProducto === p2.idProducto : p1 === p2;
  }

  onFileSelected(event: any): void { 
    this.selectedFile = event.target.files[0];
  }  

  subirImagen(): void {
    const formData = new FormData();
    formData.append('file', this.selectedFile);

    if(this.produccion.imagen){
      formData.append('oldImage', this.produccion.imagen);
    }

    this.http.post<{ ruta: string}>('http://localhost:8080/api/upload-portada', formData)
    .subscribe(res =>{
      this.produccion.imagen = res.ruta;
      this.imagenPreview = res.ruta;
    })
  }

  abrirModalDetalles(produccion: Produccion): void {
    this.produccionSeleccionado = produccion;
    this.dialog.open(this.modalDetalles);
  }

  cerrarModal(): void {
    this.dialog.closeAll();
    this.produccionSeleccionado = null;
  }


  buscarProduccion(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  cancelarEdicion(form: NgForm): void {
    this.produccion = {} as Produccion;
    this.editar = false;
    this.idEditar = null;
    this.imagenPreview = "";
    this.selectedFile = undefined!;
    form.resetForm();
    this.dialog.closeAll();
  }
 
 
  
}
