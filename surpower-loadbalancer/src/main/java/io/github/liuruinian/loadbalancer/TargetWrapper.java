package io.github.liuruinian.loadbalancer;

/**
 * @author liuruinian
 * @param <T> target class
 * <p>
 *     Target object wrapper
 * </p>
 */
public class TargetWrapper<T> {

    // ~ Fields ------------------------------------------------------------------------

    /**
     * active status
     */
    private boolean active;

    /**
     * target object
     */
    private T       target;

    // ~ Constructors ------------------------------------------------------------------

    public TargetWrapper() {
    }

    // ~ Methods -----------------------------------------------------------------------

    /**
     * build target wrapper
     * @param target    target object
     * @param <T>       target class
     * @return TargetWrapper
     */
    public static <T> TargetWrapper<T> of(T target) {
        if (target == null) {
            throw new NullPointerException("target object is null!");
        }

        TargetWrapper<T> targetWrapper = new TargetWrapper<>();
        targetWrapper.target = target;

        return targetWrapper;
    }

    /**
     * get active status
     * @return active status
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * set active status
     * @param active active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * get target object
     * @return target object
     */
    public T getTarget() {
        return this.target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetWrapper<?> that = (TargetWrapper<?>) o;
        return active == that.active &&
                target.equals(that.target);
    }

    @Override
    public int hashCode() {
        return target.hashCode();
    }
}
