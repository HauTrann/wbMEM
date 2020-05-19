import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { Router } from '@angular/router';

@Component({
  selector: 'jhi-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['menu.scss']
})
export class MenuComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;

  constructor(private accountService: AccountService, private loginModalService: LoginModalService, protected router: Router) {}

  ngOnInit(): void {}

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  navigate(name: string): void {
    switch (name) {
      case 'department':
        this.router.navigate(['/department']);
        break;
      case 'user-management':
        this.router.navigate(['/admin/user-management']);
        break;
      case 'equipment-type':
        this.router.navigate(['/equipment-type']);
        break;
      case 'equipment':
        this.router.navigate(['/equipment']);
        break;
      case 'medical-supplies':
        this.router.navigate(['/medical-supplies']);
        break;
      case 'medical-supplies-type':
        this.router.navigate(['/medical-supplies-type']);
        break;
    }
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }
}
