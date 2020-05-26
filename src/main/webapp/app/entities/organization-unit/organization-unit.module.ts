import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalDeviceMngSharedModule } from 'app/shared/shared.module';
import { OrganizationUnitComponent } from './organization-unit.component';
import { OrganizationUnitDetailComponent } from './organization-unit-detail.component';
import { OrganizationUnitUpdateComponent } from './organization-unit-update.component';
import { OrganizationUnitDeleteDialogComponent } from './organization-unit-delete-dialog.component';
import { organizationUnitRoute } from './organization-unit.route';

@NgModule({
  imports: [MedicalDeviceMngSharedModule, RouterModule.forChild(organizationUnitRoute)],
  declarations: [
    OrganizationUnitComponent,
    OrganizationUnitDetailComponent,
    OrganizationUnitUpdateComponent,
    OrganizationUnitDeleteDialogComponent
  ],
  entryComponents: [OrganizationUnitDeleteDialogComponent]
})
export class MedicalDeviceMngOrganizationUnitModule {}
