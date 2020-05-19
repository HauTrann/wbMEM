import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { MENU_ROUTE } from './menu.route';
import { MenuComponent } from './menu.component';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild([MENU_ROUTE])],
  exports: [MenuComponent],
  declarations: [MenuComponent]
})
export class MedicalDeviceMngMenuModule {}
