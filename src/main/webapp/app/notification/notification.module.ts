import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { NOTIFICATION_ROUTE } from './notification.route';
import { NotificationComponent } from './notification.component';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild([NOTIFICATION_ROUTE])],
  exports: [NotificationComponent],
  declarations: [NotificationComponent]
})
export class MedicalDeviceMngNotificationModule {}
