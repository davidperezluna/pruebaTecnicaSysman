import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { CommonModule } from '@angular/common';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Material } from '../../../core/model/material.model';
import { MaterialService } from '../../../core/servicio/material.service';
import { AlertaService } from '../../../core/servicio/alerta.service';
import {MatSelectModule} from '@angular/material/select';
import { fechaPosteriorValidator } from '../../../core/validators/fecha.validator';
import { CiudadService } from '../../../core/servicio/ciudad.service';
import { GenericResponse } from '../../../core/model/generic-response';
import { Ciudad } from '../../../core/model/ciudad.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatGridListModule,
    MatSelectModule
  ],
})
export class FormComponent implements OnInit {
  materialForm: FormGroup;
  ciudades: Ciudad[] = [];
  estados = [
    {value: 'Activo', viewValue: 'Activo'},
    {value: 'Disponible', viewValue: 'Disponible'},
    {value: 'Asignado', viewValue: 'Asignado'}
  ];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<FormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Material,
    private materialService: MaterialService,
    private ciudadService: CiudadService,
    private alertaService: AlertaService
  ){
    this.materialForm = this.fb.group({
      id: [],
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      tipo: ['', Validators.required],
      fechaCompra: [new Date(), Validators.required],
      fechaVenta: [new Date(),Validators.required],
      estado: ['', Validators.required],
      idCiudad: ['', Validators.required],
    }, { validators: fechaPosteriorValidator() });

    if (data) {
      this.materialForm.patchValue(data);
    }
  }
  ngOnInit(): void {
    this.getCiudades();
  }

  getCiudades(){
    this.ciudadService.getAll().subscribe({
      next:(response: GenericResponse)=>{
        let data: any = response.data;
        this.ciudades = data;
      }
    });
  }

  guardar(): void {
    if (this.materialForm.valid) {
      const material: Material = this.materialForm.value;
      let requestObservable: Observable<any>;
      if (material.id === null) {
        // Prepara la petición de actualización si el material ya tiene id
        requestObservable = this.materialService.crear(material);
      } else {
        // Prepara la petición de creación si el material es nuevo
        requestObservable = this.materialService.actualizar(material);
      }

      requestObservable.subscribe({
        next: (response) => {
          this.alertaService.succes(response.message);
          this.dialogRef.close(response.data);
        },
        error: (error) => {
          if (error.status === 409) {
            this.alertaService.warning(error.error.message);
          } else if (error.status === 500) {
            this.alertaService.error(error.error.message);
          } else {
            this.alertaService.error('Ha ocurrido un error inesperado.');
          }
        },
      });
    }
  }
}
 


