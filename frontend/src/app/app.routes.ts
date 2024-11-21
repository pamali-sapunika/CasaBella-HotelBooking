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
import { AdddiscountComponent } from './components/adddiscount/adddiscount.component';
import { ShowbookingComponent } from './components/showbooking/showbooking.component';
import { ConfirmBookingComponent } from './components/confirm-booking/confirm-booking.component';
import { MybookingsComponent } from './components/mybookings/mybookings.component';
import { RegisterComponent } from './components/register/register.component';
import { ShownewcontractComponent } from './components/shownewcontract/shownewcontract.component';
import { ConfirmContractComponent } from './components/confirm-contract/confirm-contract.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';

export const routes: Routes = [

    {
        path: '',
        redirectTo: 'home',
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
            {path: 'register', component: RegisterComponent},
            {path: 'addHotel',component: AddhotelComponent, canActivate: [authGuard]},
            {path: 'addSeasonalRooms',component: AddseasonalroomsComponent, canActivate: [authGuard]},
            {path: 'addSeasonalSupplements', component: AddseasonalsuppleComponent, canActivate: [authGuard]},
            {path: 'addSeasons/:contractId',component: AddSeasonsComponent, canActivate: [authGuard]},
            {path: 'addContract/:hotelId',component: AddcontractComponent, canActivate: [authGuard]},
            {path: 'availableHotel/:hotelId', component: AvailabilityComponent},
            {path: 'hotelslist',component: HotellistComponent, canActivate: [authGuard]},
            {path: 'hotel/:hotelId',component: ViewhotelComponent, canActivate: [authGuard]},
            {path: 'hotel/hotelcontracts/:hotelId', component: HotelContractsComponent, canActivate: [authGuard]},
            {path: 'seasonalItems/:contractId', component: SeasonalitemsComponent, canActivate: [authGuard]},
            {path: 'addDiscounts/:contractId', component: AdddiscountComponent, canActivate: [authGuard]},
            {path: 'showBooking/:bookingId', component: ShowbookingComponent, canActivate: [authGuard]},
            {path: 'confirmBooking', component: ConfirmBookingComponent, canActivate: [authGuard]},
            {path: 'myBookings', component: MybookingsComponent, canActivate: [authGuard]},
            {path: 'showContract/:contractId', component: ShownewcontractComponent, canActivate: [authGuard]},
            {path: 'confirmContract/:contractId', component: ConfirmContractComponent, canActivate: [authGuard]},
            {path: 'adminDashboard', component: AdminDashboardComponent, canActivate: [authGuard]},
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