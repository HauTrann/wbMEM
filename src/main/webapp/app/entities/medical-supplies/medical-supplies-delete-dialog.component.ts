import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedicalSupplies } from 'app/shared/model/medical-supplies.model';
import { MedicalSuppliesService } from './medical-supplies.service';

@Component({
  templateUrl: './medical-supplies-delete-dialog.component.html'
})
export class MedicalSuppliesDeleteDialogComponent {
  medicalSupplies?: IMedicalSupplies;

  constructor(
    protected medicalSuppliesService: MedicalSuppliesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.medicalSuppliesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('medicalSuppliesListModification');
      this.activeModal.close();
    });
  }
}
