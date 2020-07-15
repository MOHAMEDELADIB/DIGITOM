package burullus.digitom.app.ui.PhotoActivity

class SlidePresenter(val view: SlideMvpView) : SlideMvpPresenter {
    override fun loaddata() {
        view.getdata()
    }

    override fun backpressed() {
        view.back()
    }

    override fun nextimg() {
        view.nextimag()
        view.getdata()
    }

    override fun perviousimg() {
        view.perviousimg()
        view.getdata()
    }

}