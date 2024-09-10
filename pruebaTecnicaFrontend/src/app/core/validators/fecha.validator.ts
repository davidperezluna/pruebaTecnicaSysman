import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function fechaPosteriorValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const fechaInicio = control.get('fechaCompra')?.value;
    const fechaFin = control.get('fechaVenta')?.value;

    if (fechaInicio && fechaFin && fechaFin < fechaInicio) {
      return { fechaInvalida: true };
    }
    return null;
  };
}