import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { MedicalSuppliesComponent } from './medical-supplies.component';
import { MedicalSuppliesDetailComponent } from './medical-supplies-detail.component';
import { MedicalSuppliesUpdateComponent } from './medical-supplies-update.component';
import { MedicalSuppliesDeleteDialogComponent } from './medical-supplies-delete-dialog.component';
import { medicalSuppliesRoute } from './medical-supplies.route';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild(medicalSuppliesRoute)],
  declarations: [
    MedicalSuppliesComponent,
    MedicalSuppliesDetailComponent,
    MedicalSuppliesUpdateComponent,
    MedicalSuppliesDeleteDialogComponent
  ],
  entryComponents: [MedicalSuppliesDeleteDialogComponent]
})
export class MedicalDeviceMngMedicalSuppliesModule {}
