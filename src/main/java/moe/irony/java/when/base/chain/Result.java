package moe.irony.java.when.base.chain;

public class Result<T> implements ChainedResult<T> {

  private final T result;

  public Result(T result) {
    this.result = result;
  }

  public T getResult() {
    return this.result;
  }
}
