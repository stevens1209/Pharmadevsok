import { Component, OnInit, AfterViewInit, TemplateRef, ViewChild } from '@angular/core';
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

@Component({
  selector: 'app-produccion',
  standalone: false,
  templateUrl: './produccion.html',
  styleUrls: ['./produccion.css']
})
export class ProduccionComponent implements OnInit, AfterViewInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  
  producciones: Produccion[] = [];
  productos: Producto[] = [];
  produccion: Produccion = {} as Produccion;
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<Produccion>;
  produccionSeleccionado: Produccion | null = null;
  mostrarColumnas: string[] = ['detalles', 'idProuccion', 'Fechaproduccion', 'Fechavencimiento', 'Lote', 'Cantidadproducida', 'producto', 'acciones'];

  imagenPreview: string = "";
  selectedFile!: File;

  @ViewChild('modalProduccion') modalProducccion!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private produccionService: ProduccionService,
    private productoService: ProductoService,
    public dialog: MatDialog,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.findAll();
    this.cargarProductos();
  }

  ngAfterViewInit(): void {
    // Asegúrate de que dataSource ya se haya inicializado
    if (this.dataSource) {
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
  }

  findAll(): void {
    this.produccionService.findAll().subscribe(data => {
      this.producciones = data;
      this.dataSource = new MatTableDataSource(this.producciones);
      
      // Asignar paginator y sort solo después de inicializar dataSource
      if (this.paginator && this.sort) {
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
  }

  cargarProductos(): void {
    this.productoService.findAll().subscribe(data => {
      this.productos = data;
    });
  }

  abrirModalProduccion(editar: boolean = false, produccion?: Produccion): void {
    if (editar && produccion) {
      this.produccion = { ...produccion };
      this.editar = true;
      this.idEditar = produccion.idProduccion;
      this.imagenPreview = produccion.imagen ? produccion.imagen : "";
    } else {
      this.produccion = {} as Produccion;
      this.editar = false;
      this.idEditar = null;
      this.imagenPreview = "";
      this.selectedFile = undefined!;
    }
    this.dialog.open(this.modalProducccion);
  }

  abrirModalDetalles(produccion: Produccion): void {
    this.produccionSeleccionado = produccion;
    this.dialog.open(this.modalDetalles);
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

  onFileSelected(event: any): void {
    if(event.target.files && event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
      const reader = new FileReader();
      reader.onload = e => this.imagenPreview = reader.result as string;
      reader.readAsDataURL(this.selectedFile);
    }
  }

  guardarProduccion(): void {
    if (this.selectedFile) {
      this.subirImagen().then(imagenUrl => {
        this.produccion.imagen = imagenUrl;
        this.guardarDatosProduccion();
      }).catch(() => {
        Swal.fire('Error', 'No se pudo subir la imagen', 'error');
      });
    } else {
      this.guardarDatosProduccion();
    }
  }

  private guardarDatosProduccion(): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
    } else {
      this.save();
    }
    this.dialog.closeAll();
  }

  private subirImagen(): Promise<string> {
    return new Promise((resolve, reject) => {
      const formData = new FormData();
      formData.append('file', this.selectedFile);

      fetch('http://localhost:8080/api/upload-portada', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(data => {
        if (data.ruta) {
          resolve(data.ruta);
        } else {
          reject();
        }
      })
      .catch(() => reject());
    });
  }

  save(): void {
    this.produccionService.save(this.produccion).subscribe(() => {
      this.produccion = {} as Produccion;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar) {
      this.produccionService.update(this.idEditar, this.produccion).subscribe(() => {
        this.produccion = {} as Produccion;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(produccion: Produccion): void {
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
        this.produccionService.delete(produccion.idProduccion).subscribe(() => {
          this.findAll();
          Swal.fire('Eliminado', 'La producción ha sido eliminada', 'success');
        });
      }
    });
  }

  buscarProduccion(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }
}
