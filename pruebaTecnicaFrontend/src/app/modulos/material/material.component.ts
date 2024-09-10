import { Component, OnInit } from '@angular/core';
import { MaterialService } from '../../core/servicio/material.service';
import { GenericResponse } from '../../core/model/generic-response';
import { Material } from '../../core/model/material.model';
import {MatTableModule} from '@angular/material/table';
import {MatSelectModule} from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon'; 
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import { FormGroup, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { FormComponent } from './form/form.component';
import { CiudadService } from '../../core/servicio/ciudad.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Filter } from '../../core/model/filter.model';

@Component({
  selector: 'app-material',
  templateUrl: './material.component.html',
  styleUrls: ['./material.component.css'],
  standalone: true,
  imports: [
    MatTableModule,
    ReactiveFormsModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatDatepickerModule,
    MatNativeDateModule]
})
export class MaterialComponent implements OnInit {
  materiales: Material[] = [];
  ciudades: Material[] = [];
  searchForm: FormGroup;
  displayedColumns: string[] = ['id', 'nombre','tipo', 'fechaCompra', 'fechaVenta','ciudad','actions'];
  constructor(private materialService: MaterialService,public dialog: MatDialog, private fb: FormBuilder,private ciudadService: CiudadService,) { 
    this.searchForm = this.fb.group({
      tipo: [''],
      idCiudad: [''],
      fechaCompra: [new Date(),],
    });
  }

  ngOnInit() {  
    this.loadMaterial();
    this.loadCiudades();
  }

  loadCiudades(){
    this.ciudadService.getAll().subscribe({
      next:(response: GenericResponse)=>{
        let data: any = response.data;
        this.ciudades = data;
      }
    });
  }

  loadMaterial(){
    this.materialService.getAll().subscribe({
      next:(response: GenericResponse)=>{
        let data: any = response.data;
        this.materiales = data;
      }
    });
  }

  openDialog(material?: Material): void {
    const dialogRef = this.dialog.open(FormComponent, {
      width: '500px',
      data: material,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadMaterial();
      }
    });
  }

  limpiar(){
    this.searchForm.reset();
    this.loadMaterial();
  }

  filtrar(){
    if (this.searchForm.value.idCiudad) {
      this.findByCiudad();
    }else{
      this.findByTipoAndFechaCompra();
    }
  }

  findByCiudad(){
    this.materialService.findByCiudad(this.searchForm.value).subscribe({
      next:(response: GenericResponse)=>{
        let data: any = response.data;
        this.materiales = data;
      }
    });
  }
  findByTipoAndFechaCompra(){
    this.materialService.findByTipoAndFechaCompra(this.searchForm.value).subscribe({
      next:(response: GenericResponse)=>{
        let data: any = response.data;
        this.materiales = data;
      }
    });
  }

}
