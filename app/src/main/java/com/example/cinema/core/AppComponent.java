package com.example.cinema.core;

import com.example.cinema.core.modules.ApiModule;
import com.example.cinema.features.MainActivity;
import com.example.cinema.features.feed.FeedPresenter;
import dagger.Component;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {

    void inject(@NotNull FeedPresenter feedPresenter);

    void inject(@NotNull MainActivity mainActivity);
}

