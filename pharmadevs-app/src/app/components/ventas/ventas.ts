import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Ventas } from '../../model/ventas.model';
import { MatTableDataSource } from '@angular/material/table';
import { VentasService } from '../../services/ventas';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ViewportRuler } from '@angular/cdk/scrolling';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { Cliente } from '../../model/cliente.model';
import { ClienteService } from '../../services/cliente';
import { FacturaVenta } from '../../model/factura-venta.model';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ventas',
  standalone: false,
  templateUrl: './ventas.html',
  styleUrl: './ventas.css'
})
export class VentasComponent implements OnInit {

  ventas: Ventas[] = [];
  clientes: Cliente[] = [];
  facturas: FacturaVenta[] = [];
  venta: Ventas = {} as Ventas;
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MatTableDataSource<Ventas>;
  selectFile: any;
  ventaSeleccionado: Ventas | null = null;

  mostrarColumnas: string[] = ['detalles', 'idVentas', 'fechaventa', 'formapago', 'totalventa', 'cliente', 'facturaventa', 'acciones'];

  @ViewChild('formularioVenta') formularioVenta!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalVenta') modalVenta!: TemplateRef<any>;
  @ViewChild('modalDetalles') modalDetalles!: TemplateRef<any>;

  constructor(
    private ventasService: VentasService,
    private clienteService: ClienteService,
    private dialog: MatDialog,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.findAll();
    this.cargarClientes();
    this.cargarFacturas();
  }

  findAll(): void {
    this.ventasService.findAll().subscribe(data => {
      console.log(data);
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  cargarClientes(): void {
    this.clienteService.findAll().subscribe(data => {
      this.clientes = data;
    });
  }

  cargarFacturas(): void {
    this.ventasService.findAll().subscribe(data => {
      this.facturas = data.map(venta => venta.facturaventa); // Assuming facturaventa exists in Ventas
    });
  }

  save(): void {
    this.ventasService.save(this.venta).subscribe(() => {
      this.venta = {} as Ventas;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.ventasService.update(this.idEditar, this.venta).subscribe(() => {
        this.venta = {} as Ventas;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void {
    Swal.fire({
      title: 'Desea eliminar la venta?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.ventasService.delete(this.venta.idVentas).subscribe(() => {
          this.findAll();
          Swal.fire('Eliminado!', 'La venta ha sido eliminada.', 'success');
        });
      } else {
        this.venta = {} as Ventas;
      }
    });
  }

  editarVenta(venta: Ventas): void {
    this.venta = { ...venta };
    this.editar = true;
    this.idEditar = venta.idVentas;
    this.formularioVenta.nativeElement.scrollIntoView({ behavior: 'smooth' });
  }

  editarVentaCancelar(form: NgForm): void {
    this.venta = {} as Ventas;
    this.editar = false;
    this.idEditar = null;
    form.resetForm();
  }

  guardarVenta(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    } else {
      this.save();
      form.resetForm();
    }
    this.dialog.closeAll();
  }

  buscarVenta(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  filtroVenta(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  nombreCliente(cliente: Cliente): string {
    return `${cliente?.nombre ?? ''}`;
  }

  abrirModal(venta?: Ventas): void {
    if (venta) {
      this.venta = { ...venta };
      this.editar = true;
      this.idEditar = venta.idVentas;
    } else {
      this.venta = {} as Ventas;
      this.editar = false;
      this.idEditar = null;
    }

    this.dialog.open(this.modalVenta, {
      width: '800px',
      disableClose: true
    });
  }

  compararCliente(c1: Cliente, c2: Cliente): boolean {
    return c1 && c2 ? c1.idCliente === c2.idCliente : c1 === c2;
  }

  onFileSelected(event: any): void {
    this.selectFile = event.target.files[0];
  }

  abrirModalDetalles(venta: Ventas): void {
    this.ventaSeleccionado = venta;
    this.dialog.open(this.modalDetalles, {
      width: '500px'
    });
  }

  cerrarModal(): void {
    this.dialog.closeAll();
    this.ventaSeleccionado = null;
  }
}
