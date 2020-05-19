import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { ACC_USER_ROUTE } from './acc_user.route';
import { AccUserComponent } from './acc_user.component';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild([ACC_USER_ROUTE])],
  exports: [AccUserComponent],
  declarations: [AccUserComponent]
})
export class MedicalDeviceMngAccUserModule {}
