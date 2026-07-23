import React, { useState } from 'react';

function CurrencyConvertor() {
  const [inr, setInr] = useState('');
  const [euro, setEuro] = useState(0);

  const handleSubmit = (e) => {
    e.preventDefault();
    const rate = 0.011;
    setEuro(parseFloat(inr) * rate);
  };

  return (
    <div>
      <h3>INR to Euro Converter</h3>
      <form onSubmit={handleSubmit}>
        <input 
          type="number" 
          value={inr}
          onChange={(e) => setInr(e.target.value)}
          required
        />
        <button type="submit">Convert</button>
      </form>
      {euro > 0 && <p>Euro: {euro.toFixed(2)}</p>}
    </div>
  );
}

export default function App() {
  const [counter, setCounter] = useState(0);
  const [log, setLog] = useState('');

  const incrementValue = () => {
    setCounter(prev => prev + 1);
  };

  const sayHello = () => {
    setLog('Hello message');
  };

  const handleIncrement = () => {
    incrementValue();
    sayHello();
  };

  const handleSayWelcome = (arg) => {
    setLog('Welcome ' + arg);
  };

  const handleSynthetic = (e) => {
    setLog('Synthetic event clicked');
    alert('I was clicked');
  };

  return (
    <div>
      <h2>Event Examples</h2>
      <p>Counter: {counter}</p>
      <button onClick={handleIncrement}>Increment</button>
      <button onClick={() => setCounter(counter - 1)}>Decrement</button>
      <br /><br />
      <button onClick={() => handleSayWelcome('welcome')}>Say Welcome</button>
      <button onClick={handleSynthetic}>Synthetic</button>
      <p>{log}</p>

      <CurrencyConvertor />
    </div>
  );
}
