import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { MedicalDeviceMngMenuModule } from 'app/menu/menu.module';
import { MedicalDeviceMngNotificationModule } from 'app/notification/notification.module';
import { MedicalDeviceMngAccUserModule } from 'app/account_user/acc_user.module';

@NgModule({
  imports: [
    MedicalDeviceMngSharedModule,
    RouterModule.forChild([HOME_ROUTE]),
    MedicalDeviceMngMenuModule,
    MedicalDeviceMngNotificationModule,
    MedicalDeviceMngAccUserModule
  ],
  declarations: [HomeComponent]
})
export class MedicalDeviceMngHomeModule {}
