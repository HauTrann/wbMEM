import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { EquipmentTypeComponent } from './equipment-type.component';
import { EquipmentTypeDetailComponent } from './equipment-type-detail.component';
import { EquipmentTypeUpdateComponent } from './equipment-type-update.component';
import { EquipmentTypeDeleteDialogComponent } from './equipment-type-delete-dialog.component';
import { equipmentTypeRoute } from './equipment-type.route';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild(equipmentTypeRoute)],
  declarations: [EquipmentTypeComponent, EquipmentTypeDetailComponent, EquipmentTypeUpdateComponent, EquipmentTypeDeleteDialogComponent],
  entryComponents: [EquipmentTypeDeleteDialogComponent]
})
export class MedicalDeviceMngEquipmentTypeModule {}
