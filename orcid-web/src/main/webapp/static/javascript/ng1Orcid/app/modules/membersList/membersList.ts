import * as angular 
    from 'angular';

import { CommonModule } 
    from '@angular/common';

import { FormsModule }
    from '@angular/forms';

import { Directive, NgModule } 
    from '@angular/core';

import { downgradeComponent, UpgradeModule } 
    from '@angular/upgrade/static';

import { CommonNg2Module }
    from './../common/common.ts';

import { UniquePipe }
    from '../../pipes/uniqueNg2.ts';

//In the end only membersListNg2 should remain
import { MembersListComponent } from './membersList.component.ts';

// This is the Angular 1 part of the module
export const MembersListModule = angular.module(
    'MembersListModule', 
    []
);

// This is the Angular 2 part of the module
@NgModule(
    {
        declarations: [ 
            MembersListComponent,
            UniquePipe
        ],
        entryComponents: [ 
            MembersListComponent 
        ],
        imports: [
            CommonModule,
            CommonNg2Module,
            FormsModule,
        ]
    }
)
export class MembersListNg2Module {}

// components migrated to angular 2 should be downgraded here
//Must convert as much as possible of our code to directives

MembersListModule.directive(
    'membersListNg2', 
    <any>downgradeComponent(
        {
            component: MembersListComponent,
            //inputs: ['text']
        }
    )
);
