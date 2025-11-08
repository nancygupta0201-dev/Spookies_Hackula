# Mathematical Notation Support - Complete Guide

## Overview

Your AI assistant now supports rich mathematical notation! LaTeX-style notation is automatically
converted to beautiful Unicode mathematical symbols.

## How It Works

### 1. System Prompt Enhancement

The AI is instructed to use LaTeX notation when responding to mathematical questions. This ensures
consistent formatting.

### 2. Automatic Symbol Conversion

The `formatMathematicalText()` function automatically converts LaTeX notation to Unicode symbols in
real-time as the AI generates responses.

## Supported Mathematical Symbols

### Greek Letters (Lowercase)

| LaTeX | Symbol | Example |
|-------|--------|---------|
| `\alpha` | α | "The angle \alpha is 45°" → "The angle α is 45°" |
| `\beta` | β | "The \beta particle" → "The β particle" |
| `\gamma` | γ | "Gamma \gamma ray" → "Gamma γ ray" |
| `\delta` | δ | "Change \delta x" → "Change δ x" |
| `\epsilon` | ε | "Epsilon \epsilon value" → "Epsilon ε value" |
| `\theta` | θ | "Angle \theta" → "Angle θ" |
| `\lambda` | λ | "Wavelength \lambda" → "Wavelength λ" |
| `\mu` | μ | "Mean \mu" → "Mean μ" |
| `\pi` | π | "Pi \pi equals 3.14" → "Pi π equals 3.14" |
| `\sigma` | σ | "Standard deviation \sigma" → "Standard deviation σ" |
| `\phi` | φ | "Golden ratio \phi" → "Golden ratio φ" |
| `\omega` | ω | "Angular frequency \omega" → "Angular frequency ω" |

### Greek Letters (Uppercase)

| LaTeX | Symbol | Example |
|-------|--------|---------|
| `\Delta` | Δ | "Change \Delta y" → "Change Δ y" |
| `\Sigma` | Σ | "Sum \Sigma" → "Sum Σ" |
| `\Omega` | Ω | "Resistance \Omega" → "Resistance Ω" |
| `\Pi` | Π | "Product \Pi" → "Product Π" |
| `\Theta` | Θ | "Big-O \Theta" → "Big-O Θ" |
| `\Lambda` | Λ | "Lambda \Lambda" → "Lambda Λ" |
| `\Phi` | Φ | "Phi \Phi" → "Phi Φ" |
| `\Gamma` | Γ | "Gamma function \Gamma" → "Gamma function Γ" |

### Mathematical Operators

| LaTeX | Symbol | Meaning |
|-------|--------|---------|
| `\int` | ∫ | Integral |
| `\sum` | ∑ | Summation |
| `\prod` | ∏ | Product |
| `\sqrt` | √ | Square root |
| `\infty` | ∞ | Infinity |
| `\partial` | ∂ | Partial derivative |
| `\nabla` | ∇ | Gradient/Del operator |
| `\therefore` | ∴ | Therefore |
| `\because` | ∵ | Because |

### Comparison & Relations

| LaTeX | Symbol | Meaning |
|-------|--------|---------|
| `\leq` or `\le` | ≤ | Less than or equal to |
| `\geq` or `\ge` | ≥ | Greater than or equal to |
| `\neq` or `\ne` | ≠ | Not equal to |
| `\approx` | ≈ | Approximately equal to |
| `\equiv` | ≡ | Equivalent to |
| `\sim` | ∼ | Similar to |
| `\propto` | ∝ | Proportional to |
| `<=` | ≤ | Less than or equal (plain text) |
| `>=` | ≥ | Greater than or equal (plain text) |
| `!=` | ≠ | Not equal (plain text) |

### Set Theory

| LaTeX | Symbol | Meaning |
|-------|--------|---------|
| `\in` | ∈ | Element of |
| `\notin` | ∉ | Not an element of |
| `\subset` | ⊂ | Subset of |
| `\supset` | ⊃ | Superset of |
| `\subseteq` | ⊆ | Subset or equal to |
| `\supseteq` | ⊇ | Superset or equal to |
| `\cup` | ∪ | Union |
| `\cap` | ∩ | Intersection |
| `\emptyset` | ∅ | Empty set |
| `\forall` | ∀ | For all |
| `\exists` | ∃ | There exists |

### Arrows

| LaTeX | Symbol | Meaning |
|-------|--------|---------|
| `\rightarrow` | → | Right arrow |
| `\leftarrow` | ← | Left arrow |
| `\leftrightarrow` | ↔ | Left-right arrow |
| `\Rightarrow` | ⇒ | Implies |
| `\Leftarrow` | ⇐ | Implied by |
| `\Leftrightarrow` | ⇔ | If and only if |
| `\uparrow` | ↑ | Up arrow |
| `\downarrow` | ↓ | Down arrow |

### Basic Operators

| LaTeX | Symbol | Meaning |
|-------|--------|---------|
| `\cdot` | · | Dot product/multiplication |
| `\times` | × | Times/cross product |
| `\div` | ÷ | Division |
| `\pm` | ± | Plus-minus |
| `\mp` | ∓ | Minus-plus |
| `\degree` | ° | Degree symbol |
| `\circ` | ∘ | Composition operator |

### Superscripts & Subscripts

| Pattern | Symbol | Example |
|---------|--------|---------|
| `^2` | ² | "x^2" → "x²" |
| `^3` | ³ | "x^3" → "x³" |
| `^1` | ¹ | "x^1" → "x¹" |
| `_0` | ₀ | "x_0" → "x₀" |
| `_1` | ₁ | "x_1" → "x₁" |
| `_2` | ₂ | "x_2" → "x₂" |
| `_3` | ₃ | "x_3" → "x₃" |

### Plain Text Auto-Conversion

These common words are automatically converted when they appear as complete words:

| Plain Text | Symbol | Notes |
|------------|--------|-------|
| `pi` or `PI` | π | Must be a complete word |
| `infinity` | ∞ | Any capitalization |
| `theta` | θ | Case-insensitive |
| `alpha` | α | Case-insensitive |
| `beta` | β | Case-insensitive |
| `gamma` | γ | Case-insensitive |
| `delta` | δ | Case-insensitive |
| `sigma` | σ | Case-insensitive |
| `omega` | ω | Case-insensitive |
| `lambda` | λ | Case-insensitive |

## Example Responses

### Example 1: Pythagorean Theorem

**User asks:** "Explain the Pythagorean theorem"

**AI Response (before formatting):**

```
The Pythagorean theorem states that in a right triangle, 
a^2 + b^2 = c^2, where c is the hypotenuse.
```

**Displayed (after formatting):**

```
The Pythagorean theorem states that in a right triangle, 
a² + b² = c², where c is the hypotenuse.
```

### Example 2: Circle Area

**User asks:** "What's the formula for the area of a circle?"

**AI Response (before formatting):**

```
The area of a circle is A = \pi r^2, where r is the radius.
For a circle with radius 5, the area is approximately 
\pi \times 25 \approx 78.54 square units.
```

**Displayed (after formatting):**

```
The area of a circle is A = π r², where r is the radius.
For a circle with radius 5, the area is approximately 
π × 25 ≈ 78.54 square units.
```

### Example 3: Calculus

**User asks:** "Explain integration"

**AI Response (before formatting):**

```
Integration is the inverse of differentiation. The definite 
integral \int_a^b f(x) dx represents the area under the curve 
f(x) from a to b. As the number of rectangles approaches 
\infty, we get the exact area.
```

**Displayed (after formatting):**

```
Integration is the inverse of differentiation. The definite 
integral ∫_a^b f(x) dx represents the area under the curve 
f(x) from a to b. As the number of rectangles approaches 
∞, we get the exact area.
```

### Example 4: Set Theory

**User asks:** "Explain set union and intersection"

**AI Response (before formatting):**

```
For sets A and B:
- Union A \cup B contains all elements in A or B
- Intersection A \cap B contains elements in both A and B
- If x \in A and x \in B, then x \in A \cap B
- The empty set is denoted \emptyset
```

**Displayed (after formatting):**

```
For sets A and B:
- Union A ∪ B contains all elements in A or B
- Intersection A ∩ B contains elements in both A and B
- If x ∈ A and x ∈ B, then x ∈ A ∩ B
- The empty set is denoted ∅
```

### Example 5: Inequalities

**User asks:** "What does <= mean in math?"

**AI Response (before formatting):**

```
The symbol <= means "less than or equal to" (\leq).
For example:
- 3 <= 5 (true)
- x >= 0 means x is non-negative
- If a \neq b, then either a < b or a > b
```

**Displayed (after formatting):**

```
The symbol ≤ means "less than or equal to" (≤).
For example:
- 3 ≤ 5 (true)
- x ≥ 0 means x is non-negative
- If a ≠ b, then either a < b or a > b
```

## How the System Prompt Works

The AI receives this instruction before answering:

```
You are a helpful AI assistant. When explaining mathematical concepts:
- Use LaTeX notation for symbols (e.g., \pi for π, \alpha for α, \theta for θ)
- Use \leq for ≤, \geq for ≥, \neq for ≠
- Use \sqrt for square root, \sum for summation, \int for integral
- Use \infty for infinity
- Use proper mathematical formatting for equations
- Be clear and detailed in your explanations
```

This guides the AI to use proper LaTeX notation, which is then converted to Unicode symbols.

## Technical Implementation

### Files Modified

1. **ChatViewModel.kt**
    - Added system prompt with mathematical notation instructions
    - Prompt is prepended to every user question

2. **SpookyEdActivity.kt**
    - Enhanced `formatMathematicalText()` function
    - Added 100+ mathematical symbol mappings
    - Improved regex-based word boundary detection
    - Already called in `ChatMessageBubble` composable

## Testing Mathematical Notation

Try asking these questions to test the notation:

1. **Algebra:** "What is the quadratic formula?"
2. **Geometry:** "Explain the area of a circle"
3. **Calculus:** "What is an integral?"
4. **Greek Letters:** "What is the golden ratio phi?"
5. **Set Theory:** "Explain set operations"
6. **Inequalities:** "What does >= mean?"
7. **Physics:** "Explain Einstein's E=mc^2"
8. **Trigonometry:** "What is sin theta?"

## Limitations

1. **Complex Fractions:** Full fraction rendering (like ½ with numerator over denominator) isn't
   supported
2. **Matrices:** Matrix notation isn't fully supported
3. **Multi-line Equations:** Equations on separate lines work, but complex alignment isn't supported
4. **Superscripts:** Only ^1, ^2, ^3 are converted (higher powers show as plain text)
5. **Subscripts:** Only _0, _1, _2, _3 are converted

## Future Enhancements

Potential improvements for the future:

- [ ] Support for more superscript/subscript numbers
- [ ] Better fraction rendering
- [ ] Matrix notation support
- [ ] Equation alignment
- [ ] Inline vs block equation distinction
- [ ] Custom rendering for complex expressions

## Troubleshooting

### If Symbols Don't Appear:

1. **Check the AI model:** Smaller models might not understand LaTeX notation well
2. **Try explicit LaTeX:** Ask "Use LaTeX notation like \pi for pi"
3. **Check font support:** Ensure your device supports Unicode mathematical symbols
4. **Restart the app:** Sometimes a fresh start helps
5. **Try a different question:** Some questions naturally produce more mathematical notation

### If Plain Text Words Don't Convert:

The plain text auto-conversion only works for complete words. For example:

- ✅ "The value of pi is 3.14" → "The value of π is 3.14"
- ❌ "The pineapple" → "The pineapple" (won't convert, as "pi" is part of a word)

---

**Status:** ✅ Fully Implemented  
**Last Updated:** 2025  
**Supported Symbols:** 100+
