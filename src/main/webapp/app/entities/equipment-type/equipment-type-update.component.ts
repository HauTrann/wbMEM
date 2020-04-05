import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEquipmentType, EquipmentType } from 'app/shared/model/equipment-type.model';
import { EquipmentTypeService } from './equipment-type.service';

@Component({
  selector: 'jhi-equipment-type-update',
  templateUrl: './equipment-type-update.component.html'
})
export class EquipmentTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    description: [],
    status: []
  });

  constructor(protected equipmentTypeService: EquipmentTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ equipmentType }) => {
      this.updateForm(equipmentType);
    });
  }

  updateForm(equipmentType: IEquipmentType): void {
    this.editForm.patchValue({
      id: equipmentType.id,
      code: equipmentType.code,
      name: equipmentType.name,
      description: equipmentType.description,
      status: equipmentType.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const equipmentType = this.createFromForm();
    if (equipmentType.id !== undefined) {
      this.subscribeToSaveResponse(this.equipmentTypeService.update(equipmentType));
    } else {
      this.subscribeToSaveResponse(this.equipmentTypeService.create(equipmentType));
    }
  }

  private createFromForm(): IEquipmentType {
    return {
      ...new EquipmentType(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEquipmentType>>): void {
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
