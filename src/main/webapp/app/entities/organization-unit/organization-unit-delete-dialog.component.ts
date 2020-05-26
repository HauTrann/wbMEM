import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizationUnit } from 'app/shared/model/organization-unit.model';
import { OrganizationUnitService } from './organization-unit.service';

@Component({
  templateUrl: './organization-unit-delete-dialog.component.html'
})
export class OrganizationUnitDeleteDialogComponent {
  organizationUnit?: IOrganizationUnit;

  constructor(
    protected organizationUnitService: OrganizationUnitService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.organizationUnitService.delete(id).subscribe(() => {
      this.eventManager.broadcast('organizationUnitListModification');
      this.activeModal.close();
    });
  }
}
