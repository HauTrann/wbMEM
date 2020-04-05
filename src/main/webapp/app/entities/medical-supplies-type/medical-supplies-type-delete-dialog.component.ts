import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';
import { MedicalSuppliesTypeService } from './medical-supplies-type.service';

@Component({
  templateUrl: './medical-supplies-type-delete-dialog.component.html'
})
export class MedicalSuppliesTypeDeleteDialogComponent {
  medicalSuppliesType?: IMedicalSuppliesType;

  constructor(
    protected medicalSuppliesTypeService: MedicalSuppliesTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalSuppliesTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalSuppliesTypeListModification');
      this.activeModal.close();
    });
  }
}
