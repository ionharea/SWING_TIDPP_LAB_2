class StateManager {

    private ListState listState;
    private MessageState messageState;
    private State crtState;

    StateManager(Mediator med) {
        listState = new ListState(med);
        messageState = new MessageState(med);

        crtState = listState;
    }

    void add() {
        crtState.add();
    }

    void remove() {
        crtState.remove();
    }

    void setListState() {
        crtState = listState;
    }

    void setMessageState() {
        crtState = messageState;
    }
}
