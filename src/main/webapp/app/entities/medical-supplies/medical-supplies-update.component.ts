import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMedicalSupplies, MedicalSupplies } from 'app/shared/model/medical-supplies.model';
import { MedicalSuppliesService } from './medical-supplies.service';

@Component({
  selector: 'jhi-medical-supplies-update',
  templateUrl: './medical-supplies-update.component.html'
})
export class MedicalSuppliesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    medicalSuppliesTypeID: [],
    description: [],
    status: []
  });

  constructor(
    protected medicalSuppliesService: MedicalSuppliesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalSupplies }) => {
      this.updateForm(medicalSupplies);
    });
  }

  updateForm(medicalSupplies: IMedicalSupplies): void {
    this.editForm.patchValue({
      id: medicalSupplies.id,
      code: medicalSupplies.code,
      name: medicalSupplies.name,
      medicalSuppliesTypeID: medicalSupplies.medicalSuppliesTypeID,
      description: medicalSupplies.description,
      status: medicalSupplies.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalSupplies = this.createFromForm();
    if (medicalSupplies.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalSuppliesService.update(medicalSupplies));
    } else {
      this.subscribeToSaveResponse(this.medicalSuppliesService.create(medicalSupplies));
    }
  }

  private createFromForm(): IMedicalSupplies {
    return {
      ...new MedicalSupplies(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      medicalSuppliesTypeID: this.editForm.get(['medicalSuppliesTypeID'])!.value,
      description: this.editForm.get(['description'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalSupplies>>): void {
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
