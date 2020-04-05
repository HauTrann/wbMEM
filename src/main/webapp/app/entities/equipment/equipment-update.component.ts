import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEquipment, Equipment } from 'app/shared/model/equipment.model';
import { EquipmentService } from './equipment.service';

@Component({
  selector: 'jhi-equipment-update',
  templateUrl: './equipment-update.component.html'
})
export class EquipmentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    equipmentTypeID: [],
    status: [],
    description: []
  });

  constructor(protected equipmentService: EquipmentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ equipment }) => {
      this.updateForm(equipment);
    });
  }

  updateForm(equipment: IEquipment): void {
    this.editForm.patchValue({
      id: equipment.id,
      code: equipment.code,
      name: equipment.name,
      equipmentTypeID: equipment.equipmentTypeID,
      status: equipment.status,
      description: equipment.description
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const equipment = this.createFromForm();
    if (equipment.id !== undefined) {
      this.subscribeToSaveResponse(this.equipmentService.update(equipment));
    } else {
      this.subscribeToSaveResponse(this.equipmentService.create(equipment));
    }
  }

  private createFromForm(): IEquipment {
    return {
      ...new Equipment(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      equipmentTypeID: this.editForm.get(['equipmentTypeID'])!.value,
      status: this.editForm.get(['status'])!.value,
      description: this.editForm.get(['description'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEquipment>>): void {
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
