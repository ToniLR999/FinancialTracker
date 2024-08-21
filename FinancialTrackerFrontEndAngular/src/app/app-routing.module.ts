import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MyWalletsComponent } from './components/my-wallets/my-wallets.component';
import { PaymentsComponent } from './components/payments/payments.component';

const routes: Routes = [
  {path: 'Home', component: HomeComponent},
  {path: 'MyWallets', component: MyWalletsComponent},
  {path: 'Payments', component: PaymentsComponent},
  { path:  '**', redirectTo: '/Home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
