import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimations } from '@angular/platform-browser/animations';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient,HTTP_INTERCEPTORS,withInterceptors } from '@angular/common/http';
import { MatNativeDateModule, MAT_DATE_FORMATS,MAT_DATE_LOCALE, provideNativeDateAdapter } from '@angular/material/core';
import { NativeDateAdapter } from '@angular/material/core';
import { jwtInterceptor } from './services/jwt.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    // provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes), 
    provideAnimationsAsync('noop'),
    provideAnimations(),
    provideHttpClient(withInterceptors([jwtInterceptor])),
    MatNativeDateModule,
    { provide: MAT_DATE_LOCALE, useValue: 'en-GB' },
  ]
};
