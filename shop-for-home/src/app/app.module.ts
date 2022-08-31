import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routeComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MaterialModule } from './material/material.module';

import { AuthInterceptor } from './interceptor/auth.interceptor';
import { UserComponent } from './component/admin/user/user.component';
import { UserDetailsComponent } from './component/admin/user-details/user-details.component';
import { ProductComponent } from './component/admin/product/product.component';
import { ProductDetailsComponent } from './component/admin/product-details/product-details.component';
import { DiscountCouponComponent } from './component/admin/discount-coupon/discount-coupon.component';


@NgModule({
  declarations: [
    AppComponent,
    routeComponents,
    UserComponent,
    UserDetailsComponent,
    ProductComponent,
    ProductDetailsComponent,
    DiscountCouponComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule
  ],
  providers: [
    [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }]
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
