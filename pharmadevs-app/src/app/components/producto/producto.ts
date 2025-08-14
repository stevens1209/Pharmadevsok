import { Component,ElementRef,OnInit,ViewChild,viewChild } from '@angular/core';
import { Producto } from '../../model/producto.model';
import { MatTableDataSource } from '@angular/material/table';
import { ProductoService } from '../../services/producto';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ViewportRuler } from '@angular/cdk/scrolling';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-producto',
  standalone: false,
  templateUrl: './producto.html',
  styleUrl: './producto.css'
})
export class ProductoComponent implements OnInit {
  productos: Producto[] = [];
  producto: Producto = {} as Producto;
  editar: boolean = false;
  idEditar: number | null = null;

  dataSource!: MatTableDataSource<Producto>;
  mostrarColumnas: string[] = ['idProducto', 'nombre', 'descripcion', 'unidadMedida','categoria','preciobase', 'acciones'];

  @ViewChild('formularioProducto') formularioProducto!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private productoService: ProductoService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.productoService.findAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  save(): void {
    this.productoService.save(this.producto).subscribe(() => {
      this.producto = {} as Producto;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.productoService.update(this.idEditar, this.producto).subscribe(() => {
        this.producto = {} as Producto;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(id: number): void {
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
      if(result.isConfirmed){
        this.productoService.delete(id).subscribe(() => {
          this.findAll();
          Swal.fire('Eliminado!', 'El producto ha sido eliminado.', 'success');
        });
      }else{
      this.producto = {} as Producto;
     }
    });
  }

  editarProducto(producto: Producto): void {
    this.producto = { ...producto };
    this.editar = true;
    this.idEditar = producto.idProducto;
    this.formularioProducto.nativeElement.scrollIntoView({ behavior: 'smooth' });

    setTimeout(() => {
      this.formularioProducto.nativeElement.scrollIntoView({ behavior: 'smooth' });
    }, 100);
  }

  editarProductoCancelar(form: NgForm): void {
    this.editar = false;
    this.idEditar = null;
    this.producto = {} as Producto;
    form.resetForm();
  }

  guardarProductos(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    }else{
      this.save();
      form.resetForm();
    }
  }

  buscarProductos(event: Event){
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

}