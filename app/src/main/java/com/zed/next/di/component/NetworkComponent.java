package com.zed.next.di.component;

import com.zed.next.MainActivity;
import com.zed.next.di.module.AppModule;
import com.zed.next.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

    @Singleton
    @Component(modules = {AppModule.class, NetworkModule.class})
    public interface NetworkComponent {
        void inject(MainActivity activity);

    }

