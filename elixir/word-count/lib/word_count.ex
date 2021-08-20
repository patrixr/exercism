defmodule WordCount do
  @doc """
  Count the number of words in the sentence.

  Words are compared case-insensitively.
  """
  @spec count(String.t()) :: map
  def count(sentence) do
    rexp = ~r/[^\p{L}0-9-]+/ui
    String.split(sentence, rexp, trim: true)
      |> Enum.map(&String.downcase/1)
      |> Enum.reduce(%{}, fn word, acc ->
        Map.put(acc, word, Map.get(acc, word, 0) + 1)
      end)
  end
end
