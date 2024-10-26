import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './components/home/home.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { AddhotelComponent } from './components/addhotel/addhotel.component';
import { AvailabilityComponent } from './components/availability/availability.component';
import { HotellistComponent } from './components/hotellist/hotellist.component';
import { authGuard } from './services/authService/auth.guard';
import { ViewhotelComponent } from './components/viewhotel/viewhotel.component';
import { HotelContractsComponent } from './components/hotel-contracts/hotel-contracts.component';
import { AddcontractComponent } from './components/addcontract/addcontract.component';
import { AddSeasonsComponent } from './components/add-seasons/add-seasons.component';
import { AddseasonalroomsComponent } from './components/addseasonalrooms/addseasonalrooms.component';
import { SeasonalitemsComponent } from './components/seasonalitems/seasonalitems.component';
import { AddseasonalsuppleComponent } from './components/addseasonalsupple/addseasonalsupple.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'

    },
    {
        path: 'login',
        component: LoginComponent

    },
    {
        path: '',
        component: LayoutComponent,
        children: [
            {path: 'home', component: HomeComponent},
            {path: 'aboutus',component: AboutusComponent },
            {path: 'addHotel',component: AddhotelComponent},
            {path: 'addSeasonalRooms',component: AddseasonalroomsComponent},
            {path: 'addSeasonalSupplements', component: AddseasonalsuppleComponent, canActivate: [authGuard]},
            {path: 'addSeasons/:contractId',component: AddSeasonsComponent},
            {path: 'addContract/:hotelId',component: AddcontractComponent},
            {path: 'availability', component: AvailabilityComponent},
            {path: 'hotelslist',component: HotellistComponent, canActivate: [authGuard]},
            {path: 'hotel/:hotelId',component: ViewhotelComponent, canActivate: [authGuard]},
            {path: 'hotel/hotelcontracts/:hotelId', component: HotelContractsComponent, canActivate: [authGuard]},
            {path: 'seasonalItems/:contractId', component: SeasonalitemsComponent, canActivate: [authGuard]}
            
        ]
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes),
    ],
    exports: [
        RouterModule
    ]
})

export class AppRoutingModule { }