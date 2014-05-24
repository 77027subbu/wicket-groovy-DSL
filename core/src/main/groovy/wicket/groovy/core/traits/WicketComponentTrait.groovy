package wicket.groovy.core.traits

trait WicketComponentTrait {
    final Closure closure
    final Map<String, Closure> override

    boolean isVisible() {
        override?.visible ? override.visible.call(this) : super.isVisible()
    }

    boolean isEnabled() {
        override?.enabled ? override.enabled.call(this) : super.isEnabled()
    }

    void setOverride(Map<String, Closure> override) {
        this.override = override;
    }

    void setClosure(Closure closure){
        this.closure = closure
    }
  /**
     *  This part needs the rewrite to something more useful
     */
    void callClosure(Closure c, Object... parameters) {
        c.maximumNumberOfParameters == 1 ? c.call(parameters[0]) : c.call(parameters[0], parameters[1])
    }
}
