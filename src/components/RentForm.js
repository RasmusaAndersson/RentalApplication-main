import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../css/RentForm.css';

const RentForm = () => {
    const [carSelection, setCarSelection] = useState('');
    const [pickupDate, setPickupDate] = useState('');
    const [returnDate, setReturnDate] = useState('');
    const [driverName, setDriverName] = useState('');
    const [driverAge, setDriverAge] = useState('');
    const [carImage, setCarImage] = useState('');
    const [expectedCost, setExpectedCost] = useState(0);

    const carOptions = {
        'Volvo S60': 1500,
        'Volkswagen Golf': 1333,
        'Ford Mustang': 3000,
        'Ford Transit': 2400,
    };

    const carImages = {
        'Volvo S60': 'volvos60.jpg',
        'Volkswagen Golf': 'VolkswagenGolf.jpg',
        'Ford Mustang': 'FordMustang.jpg',
        'Ford Transit': 'FordTransit.jpg',
    };

    const handleCarSelection = (e) => {
        setCarSelection(e.target.value);
        setCarImage(carImages[e.target.value]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!carSelection || !pickupDate || !returnDate || !driverName || !driverAge) {
            alert('All fields are required.');
            return;
        }

        if (new Date(pickupDate) >= new Date(returnDate)) {
            alert('Return date must be after pickup date.');
            return;
        }

        if (driverName.length > 20) {
            alert('Driver name must be 20 characters or less.');
            return;
        }

        if (driverAge < 18 || driverAge > 100) {
            alert('Driver age must be between 18 and 100.');
            return;
        }

        const days = (new Date(returnDate) - new Date(pickupDate)) / (1000 * 60 * 60 * 24);
        const cost = days * carOptions[carSelection];
        setExpectedCost(cost);

        try {
            await axios.post('http://localhost:8080/rentals', {
                carSelection,
                pickupDate,
                returnDate,
                driverName,
                driverAge,
                pricePerDay: carOptions[carSelection],
                revenue: cost,
            });
            alert('Car rental was successful!');
        } catch (error) {
            console.error('Error submitting car rental:', error);
        }
    };

    return (
        <div className="rent-form-container">
            <div className="rent-form">
                <h1></h1>
                <form onSubmit={handleSubmit}>
                    <label>
                        Car Selection:
                        <select value={carSelection} onChange={handleCarSelection}>
                            <option value="">Select a car</option>
                            <option value="Volvo S60">Volvo S60 - 1500 kr/day</option>
                            <option value="Volkswagen Golf">Volkswagen Golf - 1333 kr/day</option>
                            <option value="Ford Mustang">Ford Mustang - 3000 kr/day</option>
                            <option value="Ford Transit">Ford Transit - 2400 kr/day</option>
                        </select>
                    </label>
                    <label>
                        Pickup Date:
                        <input type="date" value={pickupDate} onChange={(e) => setPickupDate(e.target.value)} min={new Date().toISOString().split('T')[0]} />
                    </label>
                    <label>
                        Return Date:
                        <input type="date" value={returnDate} onChange={(e) => setReturnDate(e.target.value)} min={pickupDate} />
                    </label>
                    <label>
                        Driver Name:
                        <input type="text" value={driverName} onChange={(e) => setDriverName(e.target.value)} maxLength="20" />
                    </label>
                    <label>
                        Driver Age:
                        <input type="number" value={driverAge} onChange={(e) => setDriverAge(e.target.value)} min="18" max="100" />
                    </label>
                    <button type="submit">Rent Car</button>
                </form>
                {carSelection && (
                    <div className="car-info">
                        <img src={carImage} alt={carSelection} />
                        <p>Expected Cost: {expectedCost} kr</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default RentForm;