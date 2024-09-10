import { Routes } from '@angular/router';
import { MaterialComponent } from './modulos/material/material.component';

export const routes: Routes = [
    { path: '', redirectTo: '/material', pathMatch: 'full' },
    { path: 'material', component: MaterialComponent },
];
