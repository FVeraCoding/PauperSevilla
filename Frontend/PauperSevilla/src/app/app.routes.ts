import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AdminComponent } from './pages/admin/admin.component';
import { AdminJugadoresComponent } from './pages/admin/admin-jugadores/admin-jugadores.component';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'admin', component: AdminComponent },
    { path: 'admin-jugadores', component: AdminJugadoresComponent }
];
