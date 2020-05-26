import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrganizationUnit, OrganizationUnit } from 'app/shared/model/organization-unit.model';
import { OrganizationUnitService } from './organization-unit.service';
import { UtilsService } from 'app/entities/utils/utils.service';

@Component({
  selector: 'jhi-organization-unit-update',
  templateUrl: './organization-unit-update.component.html'
})
export class OrganizationUnitUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    description: [],
    status: []
  });

  constructor(
    protected organizationUnitService: OrganizationUnitService,
    protected activatedRoute: ActivatedRoute,
    public utilsService: UtilsService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organizationUnit }) => {
      this.updateForm(organizationUnit);
    });
  }

  updateForm(organizationUnit: IOrganizationUnit): void {
    this.editForm.patchValue({
      id: organizationUnit.id,
      code: organizationUnit.code,
      name: organizationUnit.name,
      description: organizationUnit.description,
      status: organizationUnit.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organizationUnit = this.createFromForm();
    if (organizationUnit.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationUnitService.update(organizationUnit));
    } else {
      this.subscribeToSaveResponse(this.organizationUnitService.create(organizationUnit));
    }
  }

  private createFromForm(): IOrganizationUnit {
    return {
      ...new OrganizationUnit(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizationUnit>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
