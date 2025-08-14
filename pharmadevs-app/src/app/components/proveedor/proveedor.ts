import { Component,ElementRef, OnInit, ViewChild } from '@angular/core';
import { Proveedor } from '../../model/proveedor.model';
import { MatTableDataSource } from '@angular/material/table';
import { ProveedorService } from '../../services/proveedor';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ViewportRuler } from '@angular/cdk/scrolling';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-proveedor',
  standalone: false,
  templateUrl: './proveedor.html',
  styleUrl: './proveedor.css'
})
export class ProveedorComponent implements OnInit {
  Proveedor: Proveedor[] = [];
  proveedor: Proveedor = {} as Proveedor;
  editar: boolean = false;
  idEditar: number | null = null;

  dataSource!: MatTableDataSource<Proveedor>;
  mostrarColumnas: string[] = ['idProveedor', 'nombre', 'direccion', 'email', 'telefono', 'ruc', 'acciones'];

  @ViewChild('formularioProveedor') formularioProveedor!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private proveedorService: ProveedorService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.proveedorService.findAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  save(): void {
    this.proveedorService.save(this.proveedor).subscribe(() => {
      this.proveedor = {} as Proveedor;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.proveedorService.update(this.idEditar, this.proveedor).subscribe(() => {
        this.proveedor = {} as Proveedor;
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
    }).then(result => {
      if(result.isConfirmed) {
        this.proveedorService.delete(id).subscribe(() => {
          this.findAll();
          Swal.fire('Eliminado','El proveedor ha sido eliminado correctamente','success');
        });
      }else {
        this.proveedor = {} as Proveedor;
      }
    });
  }

  editarProveedor(proveedor: Proveedor): void {
    this.proveedor = { ...proveedor };
    this.editar = true;
    this.idEditar = proveedor.idProveedor;

    setTimeout(() => {
      this.formularioProveedor.nativeElement.scrollIntoView({ behavior: 'smooth' });
    }, 100);
  }

  editarProveedorCancelar(form:NgForm): void {
    this.proveedor = {} as Proveedor;
    this.editar = false;
    this.idEditar = null;
    form.resetForm();
  }

  guardarProveedor(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    }else{
      this.save();
      form.resetForm();
    }
  }

  buscarProveedor(event: Event){
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }
  
}