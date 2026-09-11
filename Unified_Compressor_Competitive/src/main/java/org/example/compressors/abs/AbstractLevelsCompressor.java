package org.example.compressors.abs;

public abstract class AbstractLevelsCompressor extends AbstractCompressor {
    int level;
    int max_level = -1;
    int min_level = -1;

    public AbstractLevelsCompressor(String name, int level) {
        super(name);
        setLevel(level);
        setName(getName() + "_" + level);
    }

    public AbstractLevelsCompressor(String name, int level, int min_level, int max_level) {
        super(name);
        setMax_level(max_level);
        setMin_level(min_level);
        setLevel(level);
        setName(getName() + "_" + level);
    }

    protected int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        if ((getMax_level() != getMin_level()) & ((level < getMin_level()) || (level > getMax_level()))) {
            if (level < getMin_level())
                this.level = getMin_level();
            else this.level = getMax_level();
        } else this.level = level;
    }

    public int getMax_level() {
        return max_level;
    }

    protected void setMax_level(int max_level) {
        this.max_level = max_level;
    }

    public int getMin_level() {
        return min_level;
    }

    protected void setMin_level(int min_level) {
        this.min_level = min_level;
    }
}
