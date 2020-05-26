import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'department',
        loadChildren: () => import('./department/department.module').then(m => m.MedicalDeviceMngDepartmentModule)
      },
      {
        path: 'department-employee',
        loadChildren: () => import('./department-employee/department-employee.module').then(m => m.MedicalDeviceMngDepartmentEmployeeModule)
      },
      {
        path: 'equipment',
        loadChildren: () => import('./equipment/equipment.module').then(m => m.MedicalDeviceMngEquipmentModule)
      },
      {
        path: 'equipment-type',
        loadChildren: () => import('./equipment-type/equipment-type.module').then(m => m.MedicalDeviceMngEquipmentTypeModule)
      },
      {
        path: 'medical-supplies',
        loadChildren: () => import('./medical-supplies/medical-supplies.module').then(m => m.MedicalDeviceMngMedicalSuppliesModule)
      },
      {
        path: 'medical-supplies-type',
        loadChildren: () =>
          import('./medical-supplies-type/medical-supplies-type.module').then(m => m.MedicalDeviceMngMedicalSuppliesTypeModule)
      },
      {
        path: 'organization-unit',
        loadChildren: () => import('./organization-unit/organization-unit.module').then(m => m.MedicalDeviceMngOrganizationUnitModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class MedicalDeviceMngEntityModule {}
