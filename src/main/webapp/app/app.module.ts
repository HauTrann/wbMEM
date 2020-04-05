import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { MedicalDeviceMngCoreModule } from 'app/core/core.module';
import { MedicalDeviceMngAppRoutingModule } from './app-routing.module';
import { MedicalDeviceMngHomeModule } from './home/home.module';
import { MedicalDeviceMngEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { SidebarComponent } from 'app/layouts/sidebar/sidebar.component';

@NgModule({
  imports: [
    BrowserModule,
    MedicalDeviceMngSharedModule,
    MedicalDeviceMngCoreModule,
    MedicalDeviceMngHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MedicalDeviceMngEntityModule,
    MedicalDeviceMngAppRoutingModule
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    SidebarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent
  ],
  bootstrap: [MainComponent]
})
export class MedicalDeviceMngAppModule {}
