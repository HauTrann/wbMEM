import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UtilsService {
  public statuss: any[] = [
    {
      status: 0,
      name: 'Không hoạt động'
    },
    {
      status: 1,
      name: 'Hoạt động'
    }
  ];

  public getStatus(status: number): string {
    if (status === 1) {
      return 'Hoạt động';
    } else {
      return 'Không hoạt động';
    }
  }
}
