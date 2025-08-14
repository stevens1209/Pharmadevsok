import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Producto } from '../../model/producto.model';
import { Almacen } from '../../model/almacen.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AlmacenService } from '../../services/almacen';
import { ProductoService } from '../../services/producto';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-almacen',
  standalone: false,
  templateUrl: './almacen.html',
  styleUrl: './almacen.css'
})
export class AlmacenComponent implements OnInit {
  almacenes: Almacen[] = [];
  productos: Producto[] = [];
  almacen: Almacen = {} as Almacen;

  editar = false;
  idEditar: number | null = null;

  dataSource!: MatTableDataSource<Almacen>;
  selectFile!: File;
  imagenPreview: string = '';
  almacenSeleccionado: Almacen | null = null;

  mostrarColumnas: string[] = ['detalles', 'idAlmacen', 'stock', 'ubicacion', 'producto', 'acciones'];

  // wrapper para scroll
  @ViewChild('formularioAlmacen') formularioAlmacen!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalAlmacen') modalAlmacen!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private almacenService: AlmacenService,
    private productoService: ProductoService,
    private dialog: MatDialog,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.findAll();
    this.cargarProducto(); // <-- carga productos para el mat-select
  }

  findAll(): void {
    this.almacenService.findAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;

    });
  }

  cargarProducto(): void {
    this.productoService.findAll().subscribe(data => {
      this.productos = data ;
    });
  }

  save(): void {
    this.almacenService.save(this.almacen).subscribe(() => {
      this.almacen = {} as Almacen;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.almacenService.update(this.idEditar, this.almacen).subscribe(() => {
        this.almacen = {} as Almacen;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void {
    Swal.fire({
      title: '¿Desea eliminar el registro?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.almacenService.delete(this.almacen.idAlmacen).subscribe(() => {
          this.findAll();
          this.almacen = {} as Almacen;
          Swal.fire('Eliminado', 'El almacen ha sido eliminado', 'success');
        });
      } else {
        this.almacen = {} as Almacen;
      }
    });
  }

  editarAlmacen(alm: Almacen): void {
    this.almacen = { ...alm };
    this.idEditar = alm.idAlmacen;
    this.editar = true;

    // Scroll suave al formulario (usa el wrapper #formularioAlmacen)
    setTimeout(() => {
      if (this.formularioAlmacen?.nativeElement) {
        this.formularioAlmacen.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
      }
    }, 100);
  }

  editarAlmacenCancelar(form: NgForm): void {
    this.almacen = {} as Almacen;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarAlmacen(): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
    } else {
      this.save();
    }
    this.dialog.closeAll();
  }

  filtroAlmacen(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
     
  }

  nombreProducto(producto: Producto): string {
    return `${producto?.nombre ?? ''}`;
  }

  abrirModal(almacen?: Almacen): void {
    //this.cargarProducto(); 
    if (almacen) {
      this.almacen = { ...almacen }; // <-- copiar el parámetro correcto
      this.editar = true;
      this.idEditar = almacen.idAlmacen;
    } else {
      this.almacen = {} as Almacen;
      this.editar = false;
      this.idEditar = null;
    }

    this.dialog.open(this.modalAlmacen, {
      width: '800px',
      disableClose: true
    });
  }

  compararProducto(p1: Producto, p2: Producto): boolean {
    return p1 && p2 ? p1.idProducto === p2.idProducto : p1 === p2;
  }

  onFileSelected(event: any): void {
    this.selectFile = event.target.files[0];
  }

  subirImagen(): void {
    const formData = new FormData();
    formData.append('file', this.selectFile);

    if (this.almacen.imagen) {
      formData.append('oldImage', this.almacen.imagen);
    }

    this.http.post<{ ruta: string }>('http://localhost:8080/api/upload-portada', formData)
      .subscribe(res => {
        this.almacen.imagen = res.ruta;
        this.imagenPreview = res.ruta;
      });
  }

  abrirModalDetalles(almacen: Almacen): void {
    this.almacenSeleccionado = almacen;
    this.dialog.open(this.modalDetalles, { // <-- abrir el modal de detalles correcto
      width: '500px'
    });
  }

  cerrarModal(): void {
    this.dialog.closeAll();
    this.almacenSeleccionado = null;
  }
}