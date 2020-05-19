import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { EquipmentComponent } from './equipment.component';
import { EquipmentDetailComponent } from './equipment-detail.component';
import { EquipmentUpdateComponent } from './equipment-update.component';
import { EquipmentDeleteDialogComponent } from './equipment-delete-dialog.component';
import { equipmentRoute } from './equipment.route';
import { QRCodeModule } from 'angular2-qrcode';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild(equipmentRoute), QRCodeModule, NgSelectModule],
  declarations: [EquipmentComponent, EquipmentDetailComponent, EquipmentUpdateComponent, EquipmentDeleteDialogComponent],
  entryComponents: [EquipmentDeleteDialogComponent]
})
export class MedicalDeviceMngEquipmentModule {}
