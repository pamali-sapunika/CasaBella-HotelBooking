import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './authService/auth.service';
import { StatusChangeEvent } from '@angular/forms';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  
  const authService = inject(AuthService);
  const token = authService.getToken();

  if(token){
    const cloned = req.clone({setHeaders:{
      Authorization: `Bearer ${token}` // Adds the token to headers
    }}); 
    return next(cloned); // Passes the cloned request
  }

  return next(req); // Passes the original request if no token

};
