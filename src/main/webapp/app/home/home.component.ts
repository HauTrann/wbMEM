import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { SELECT, SELECT_MENU } from 'app/shared/constants/app.constants';
import { JhiEventManager } from 'ng-jhipster';
import { DeviceDetectorService } from 'ngx-device-detector';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  @ViewChild('scanner')
  // scanner?: ZXingScannerComponent;
  hasDevices?: boolean;
  hasPermission?: boolean;
  qrResult: any;
  scannerEnabled?: boolean;
  selectMenu?: string;
  selected?: string;
  SELECT_MENU = SELECT_MENU;
  SELECT = SELECT;
  eventSubscriberChangeSession?: Subscription;
  selectSubscriber?: Subscription;

  currentDevice: MediaDeviceInfo | null = null;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    public deviceService: DeviceDetectorService
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    /*this.scanner?.camerasFound.subscribe((devices: MediaDeviceInfo[]) => {
      this.hasDevices = true;
      this.currentDevice = devices[0];

      for (const device of devices) {
        if (/back|rear|environment/gi.test(device.label)) {
          this.currentDevice = device;
          break;
        }
      }
      this.scannerEnabled = true;
    });

    this.scanner?.camerasNotFound.subscribe((devices: MediaDeviceInfo[]) => {
    });

    this.scanner?.permissionResponse.subscribe((answer: boolean) => {
      this.hasPermission = answer;
    });*/
    this.registerSelect();
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.selectSubscriber) {
      this.selectSubscriber?.unsubscribe();
    }
  }

  registerSelect(): void {
    this.selectSubscriber = this.eventManager.subscribe('Select', (response: { content: string | undefined }) => {
      this.selected = response.content;
    });
  }
}
