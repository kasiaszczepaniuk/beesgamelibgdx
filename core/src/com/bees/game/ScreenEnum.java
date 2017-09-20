package com.bees.game;

import com.badlogic.gdx.Screen;

public enum ScreenEnum {

    GAME {
        public Screen getScreen(Object... params) {
            return new TheGameScreen();
        }
    },

    GAME_OVER {
        public Screen getScreen(Object... params) {
            return new GameOverScreen();
        }
    },

    MENU {
        public Screen getScreen(Object... params) {
            return new MainMenuScreen();
        }
    },


    WRONG_ANSWER {
        public Screen getScreen(Object... params) {
            return new wrongAnswer();
        }
    },

    WIN {
        public Screen getScreen(Object... params) {
            return new youWinScreen();
        }
    };

    public abstract Screen getScreen(Object[] params);
}
