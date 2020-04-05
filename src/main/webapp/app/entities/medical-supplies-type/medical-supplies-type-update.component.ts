import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedicalSuppliesType, MedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';
import { MedicalSuppliesTypeService } from './medical-supplies-type.service';

@Component({
  selector: 'jhi-medical-supplies-type-update',
  templateUrl: './medical-supplies-type-update.component.html'
})
export class MedicalSuppliesTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    description: [],
    status: []
  });

  constructor(
    protected medicalSuppliesTypeService: MedicalSuppliesTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalSuppliesType }) => {
      this.updateForm(medicalSuppliesType);
    });
  }

  updateForm(medicalSuppliesType: IMedicalSuppliesType): void {
    this.editForm.patchValue({
      id: medicalSuppliesType.id,
      code: medicalSuppliesType.code,
      name: medicalSuppliesType.name,
      description: medicalSuppliesType.description,
      status: medicalSuppliesType.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalSuppliesType = this.createFromForm();
    if (medicalSuppliesType.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalSuppliesTypeService.update(medicalSuppliesType));
    } else {
      this.subscribeToSaveResponse(this.medicalSuppliesTypeService.create(medicalSuppliesType));
    }
  }

  private createFromForm(): IMedicalSuppliesType {
    return {
      ...new MedicalSuppliesType(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalSuppliesType>>): void {
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
