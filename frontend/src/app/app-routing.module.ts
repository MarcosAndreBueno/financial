import { OcurrencesModule } from './ocurrences/ocurrences.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
    //se caminho vazio (localhost:4200), vefificar todo o caminho, redirecionar para ocurrences
    { path: '', pathMatch: 'full', redirectTo: 'ocurrences' },
    //caminho para módulo filho
    {
        path: 'ocurrences',
        loadChildren: () => import('./ocurrences/ocurrences.module').then(m => m.OcurrencesModule)
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
